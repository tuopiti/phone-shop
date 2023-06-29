package com.project.phoneshop.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.phoneshop.dto.ProductOrderDTO;
import com.project.phoneshop.dto.SaleDTO;
import com.project.phoneshop.exception.ResourceNotFoundException;
import com.project.phoneshop.mapper.SaleMapper;
import com.project.phoneshop.model.Product;
import com.project.phoneshop.model.Sale;
import com.project.phoneshop.model.SaleDetail;
import com.project.phoneshop.repository.ProductRepository;
import com.project.phoneshop.repository.SaleDetailRepository;
import com.project.phoneshop.repository.SaleRepository;
import com.project.phoneshop.service.ProductService;
import com.project.phoneshop.service.SellService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellServiceImpl implements SellService{
	private final SaleRepository saleRepository;
	private final SaleDetailRepository saleDetailRepository;
	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleMapper saleMapper;

	@Override
	public void sell(SaleDTO saleDTO) {
		List<ProductOrderDTO> productOrderDTOs = saleDTO.getProducts();
		// Validation
		for(ProductOrderDTO orderDTO : productOrderDTOs) {
			//validate stock
			productService.hasAvailableUnit(orderDTO.getProductId(), orderDTO.getUnit());
			// validate sale price
			productService.salePriceIsSet(orderDTO.getProductId());
		}
		// get ordered products from db
		List<Long> productIds = productOrderDTOs.stream()
			.map(ProductOrderDTO::getProductId)
			.toList();
		
		Map<Long, Product> productMap = productRepository.findAllById(productIds)
			.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		// save sale
		Sale sale = saleMapper.toSale(saleDTO);
		saleRepository.save(sale);
		
		//save sale detail
		for(ProductOrderDTO orderDTO : productOrderDTOs) {
			Product product = productMap.get(orderDTO.getProductId());
			SaleDetail saleDetail = saleMapper.toSaleDetail(orderDTO, sale, product.getSalePrice());
			saleDetailRepository.save(saleDetail);
			
			// update stock
			product.setAvailableUnit(product.getAvailableUnit() - orderDTO.getUnit());
			productRepository.save(product);
		}
		
	}
	
	@Override
	public void cancelSale(Long saleId) {
		// validate saleId
		// get sale by id , update saleStatus
		Sale sale = getById(saleId);
		sale.setStatus(false);
		saleRepository.save(sale);
		
		//get saleDetail , update saleDetailStatus
		List<SaleDetail> saleDeatils = saleDetailRepository.findBySaleId(saleId);
		
		
		// get products by ids
		List<Long> productIds = saleDeatils.stream()
			.map(sd -> sd.getProduct().getId())
			.toList();
		List<Product> products = productRepository.findAllById(productIds);
		
		Map<Long, Product> productMap = products.stream()
			//.collect(Collectors.toMap(p->p.getId(), p -> p));
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		// update stock
		saleDeatils.forEach(sd ->{
			Product product = productMap.get(sd.getProduct().getId());
			product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
			productRepository.save(product);
		});

	}

	@Override
	public Sale getById(Long saleId) {
		return saleRepository.findById(saleId)
				.orElseThrow(() -> new ResourceNotFoundException("Sale", saleId));
	}
}
