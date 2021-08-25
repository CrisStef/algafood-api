package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.KitchenNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.service.KitchenService;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KitchenServiceT {
	/** Se não quiser que os testes sejam executados colocar o T no final do nome da classe de teste*/

	@Autowired
	private KitchenService kitchenService;

	@Test
	public void successInsertKitchenWithTest() {
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName("Mexicana");

		newKitchen = kitchenService.save(newKitchen);

		assertThat(newKitchen).isNotNull();
		assertThat(newKitchen.getId()).isNotNull();
	}

	@Test(expected = ConstraintViolationException.class)
	public void failInsertKitchenWithoutNameTest() {
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName(null);

		newKitchen = kitchenService.save(newKitchen);
	}

	@Test(expected = EntityInUseException.class)
	public void  failRemoveKitchenInUseTest() {
		kitchenService.remove(1L);
	}

	@Test(expected = KitchenNotFoundException.class)
	public void  failRemoveKitchenNotFoundTest() {
		kitchenService.remove(200L);
	}
}