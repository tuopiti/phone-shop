package com.project.phoneshop.service.impl;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.phoneshop.exception.ResourceNotFoundException;
import com.project.phoneshop.model.Model;
import com.project.phoneshop.repository.ModelRepository;
import com.project.phoneshop.service.BrandService;
import com.project.phoneshop.service.ModelService;
import com.project.phoneshop.spec.ModelFilter;
import com.project.phoneshop.spec.ModelSpec;
import com.project.phoneshop.utils.PageUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
	
	private final ModelRepository modelRepository;
	private final BrandService brandService;

	/*
	@Override
	public Model save(ModelDTO dto) {
		Long brandId = dto.getBrandDTO().getId();
		brandService.getById(brandId);
		
		Model model = ModelMapper.INSTANCE.toModel(dto);
		return modelRepository.save(model);
	}*/

	@Override
	public Model save(Model entity) {
		//brandService.getById(entity.getId());

		return modelRepository.save(entity);
	}
	
	/*
	@Override
	public Model getById(Integer id) {
		  return modelRepository.findById(id)
				  .orElseThrow(()-> new ApiException(HttpStatus.NOT_FOUND,String.format("Model not found for id=%d", id)));
				  .orElseThrow(()-> new ResourceNotFoundException("Model", id));
				
	}*/
	
	@Override
	public Model getById(Long id)  {
		return modelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Model", id));
	}

	@Override
	public Page<Model> getModels(Map<String, String> params) {
		Pageable pageable = PageUtils.getPageable(params);
		
		ModelFilter modelFilter = new ModelFilter();
		if(params.containsKey("modelId")) {
			modelFilter.setModelId(MapUtils.getInteger(params, "modelId"));
		}
		if(params.containsKey("modelName")) {
			modelFilter.setModelName(MapUtils.getString(params, "modelName"));
		}
		if(params.containsKey("brandId")) {
			modelFilter.setBrandId(MapUtils.getInteger(params, "brandId"));
		}
		if(params.containsKey("brandName")) {
			modelFilter.setBrandName(MapUtils.getString(params, "brandName"));
		}
		
		ModelSpec modelSpec = new ModelSpec(modelFilter);
		
		Page<Model> page = modelRepository.findAll(modelSpec, pageable);
		return page;
	}
	
	
//	@Override
//	public List<Model> getModels(Map<String, String> params) {
//		ModelFilter modelFilter = new ModelFilter();
//		if(params.containsKey("modelId")) {
//			modelFilter.setModelId(MapUtils.getLong(params, "modelId"));
//		}
//		if(params.containsKey("modelName")) {
//			modelFilter.setModelName(MapUtils.getString(params, "modelName"));
//		}
//		if(params.containsKey("brandId")) {
//			modelFilter.setBrandId(MapUtils.getLong(params, "brandId"));
//		}
//		if(params.containsKey("brandName")) {
//			modelFilter.setBrandName(MapUtils.getString(params, "brandName"));
//		}
//		ModelSpec modelSpec = new ModelSpec(modelFilter);
//		return modelRepository.findAll(modelSpec, Sort.by(Order.asc("id")));
//	}
	
	
//	public List<Model> getModelsOld(Map<String, String> params) {
//		Specification<Model> specification = new Specification<Model>() {
//
//			@Override
//			public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				if (params.containsKey("name")) {
//					String modelName = params.get("name");
//					Predicate predicate = cb.like(model.get("name"), "%" + modelName + "%");
//					return predicate;
//				}
//				return null;
//			}
//		};
//		
//		List<Model> list = modelRepository.findAll(specification, Sort.by(Order.asc("id")));
//		return list;
//
//	}
	
	
//	public List<Model> getModelsOld(Map<String, String> params) {
//	Specification<Model> specification = (model, query, cb) -> {
//			if (params.containsKey("name")) {
//				String modelName = params.get("name");
//				Predicate predicate = cb.like(model.get("name"), "%" + modelName + "%");
//				return predicate;
//			}
//			return null;
//		}
//	};
//	
//	List<Model> list = modelRepository.findAll(specification, Sort.by(Order.asc("id")));
//	return list;
//
//}


}
