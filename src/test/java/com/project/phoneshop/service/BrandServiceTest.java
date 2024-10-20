package com.project.phoneshop.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;

import com.project.phoneshop.dto.BrandDTO;
import com.project.phoneshop.exception.ApiException;
import com.project.phoneshop.model.Brand;
import com.project.phoneshop.repository.BrandRepository;
import com.project.phoneshop.service.impl.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BrandServiceTest {

	@Mock
	private BrandRepository brandRepository;
	
	private BrandService brandService;
	
	@Captor
	private ArgumentCaptor<Brand> brandCapture;
	
	private Brand brand;
	
	@BeforeEach
	public void setup() {
		brandService = new BrandServiceImpl(brandRepository);
		brand = new Brand(1L,"Apple", true);
		when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
	}
	
	//@Test
	public void testSavebrandOld() {
		
		//Given
		Brand brand = new Brand();
		brand.setName("Apple");
		
		//When
		
		when(brandRepository.save(any(Brand.class))).thenAnswer(new Answer<Brand>() {

			@Override
			public Brand answer(InvocationOnMock invocation) throws Throwable {
				Brand brandEntity = invocation.getArgument(0);
				brandEntity.setId(1L);
				return brandEntity;
			}
		});
		
		/*
		when(brandRepository.save(any(Brand.class))).thenAnswer(invocation ->{
			Brand brandEntity = invocation.getArgument(0);
			brandEntity.setId(1);
			return brandEntity;
		});
		*/
		
		Brand brandReturn = brandService.save(brand);
		//Then
		assertEquals("Apple", brandReturn.getName());
		assertEquals(1, brandReturn.getId());
	}
	
	@Test
	public void testSavebrand() {
		
		//Given
		Brand brand = new Brand();
		brand.setName("Apple");
		
		//When
		
		Brand brandReturn = brandService.save(brand);
		//Then
		
		verify(brandRepository, times(1)).save(brand);
	}
	
	
	@Test
	public void getByIdSuccess() {
		//Given
		//Brand brand = new Brand();
		//brand.setId(1);
		//brand.setName("Apple");
		
		//When
		//when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		
		Brand brandReturn = brandService.getById(1L);
		//then
		assertNotNull(brandReturn);
		assertEquals("Apple", brandReturn.getName());
		assertEquals(1, brandReturn.getId());
	}
	
	
	@Test
	public void  getByIdThrowException()  {
		//given
		
		//when
		when(brandRepository.findById(2L)).thenReturn(Optional.empty());
				
		//then
				
		assertThatThrownBy(() -> brandService.getById(2L))
		.isInstanceOf(ApiException.class)
		//.hasMessage("null");
		.hasMessageStartingWith("brand not found for id=");
		
	}
	
	@Test
	public void testUpdateBrand() {
		//given
		//Brand brandFromDB = new Brand(1, "Apple");
		BrandDTO brandUpdate = new BrandDTO(1L, "Apple V2", true);
		//when
		//when(brandRepository.findById(1)).thenReturn(Optional.of(brandFromDB));
		Brand brandAfterUpdate = brandService.update(1L, brandUpdate);
		
		
		//then
		//verify(brandRepository, times(1)).findById(1);
		verify(brandRepository, atMostOnce()).findById(1L);
		verify(brandRepository).save(brandCapture.capture());
		assertEquals(brandCapture.getValue().getName(), brandUpdate.getName());
		
	}
	
	@Test
	public void testDeleteBrand() {
		//given 
		Long brandToDelete = 1L;
		// when
		brandService.delete(brandToDelete);
		//then
		//verify(brandRepository, times(1)).delete(brand);
		
		verify(brandRepository).save(brandCapture.capture());
		assertEquals(false, brandCapture.getValue().getActive());
		
		verify(brandRepository, times(1)).save(brand);
	}
	
	/*
	@Test
	public void testListBrand() {
		//given
		List<Brand> brands = List.of(
				new Brand(1L, "Apple", true),
				new Brand(2L, "Samsung", true)
				);
		//when
		//when(brandRepository.findAll()).thenReturn(brands);
		when(brandRepository.findByActiveTrue()).thenReturn(brands);
		List<Brand> brandsReturn = brandService.getBrands();
		
		//then
		assertEquals(2, brandsReturn.size());
		assertEquals("Apple", brandsReturn.get(0).getName());
		assertEquals("Samsung", brandsReturn.get(1).getName());
	}
	*/
}
