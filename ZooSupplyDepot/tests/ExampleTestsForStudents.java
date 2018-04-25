package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import animalManagement.*;


public class ExampleTestsForStudents  {
	
	@Test
	public void testSimpleConstructing() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		assertTrue(list.getSize() == 0);
		assertEquals("[  ]", list.toString());
	}
	
	@Test
	public void testSimpleAdding() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		for (int i = ZooAnimal.ANIMAL_OBJECTS.length - 1; i >= 0; i--) {
			list.add(ZooAnimal.ANIMAL_OBJECTS[i]);
		}
		assertEquals(ZooAnimal.ANIMAL_OBJECTS.length, list.getSize());
		assertEquals(
			"[ " +
			"alligator, armadillo, cheetah, eagle, elephant, gecko, "+
			"jaguar, lion, panda, reindeer, squirrel, "+
			"tiger, toucan, zebra ]",
			
			list.toString()
		);
		
		list.add(ZooAnimal.ALLIGATOR);
		list.add(ZooAnimal.ARMADILLO);
		list.add(ZooAnimal.CHEETAH);
		list.add(ZooAnimal.EAGLE);
		list.add(ZooAnimal.CHEETAH);
		list.add(ZooAnimal.ELEPHANT);
		assertEquals(20, list.getSize());
		assertEquals(
				"[ " +
				"alligator, alligator, armadillo, armadillo, cheetah, cheetah, cheetah, "+
				"eagle, eagle, elephant, elephant, gecko, "+
				"jaguar, lion, panda, reindeer, squirrel, "+
				"tiger, toucan, zebra ]",
				
				list.toString()
		);
	}	
	
	
	
	@Test
	public void testSimpleRemovingSome() {
		SortedListOfImmutables list1 = new SortedListOfImmutables();
		SortedListOfImmutables list2 = new SortedListOfImmutables();

		list1.add(ZooAnimal.ALLIGATOR);
		list1.add(ZooAnimal.CHEETAH);
		list1.add(ZooAnimal.CHEETAH);
		list1.add(ZooAnimal.ELEPHANT);
		list1.add(ZooAnimal.ELEPHANT);

		list2.add(ZooAnimal.ALLIGATOR);
		list2.add(ZooAnimal.ALLIGATOR);
		list2.add(ZooAnimal.CHEETAH);
		list2.add(ZooAnimal.ELEPHANT);
		list2.add(ZooAnimal.ELEPHANT);

		list1.remove(list2);

		assertEquals("[ cheetah ]", list1.toString());
	}
	
	
}
