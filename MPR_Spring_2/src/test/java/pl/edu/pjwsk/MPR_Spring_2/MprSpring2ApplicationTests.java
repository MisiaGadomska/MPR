package pl.edu.pjwsk.MPR_Spring_2;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pjwsk.MPR_Spring_2.model.Cat;
import pl.edu.pjwsk.MPR_Spring_2.repository.CatRepository;
import pl.edu.pjwsk.MPR_Spring_2.service.CatService;
import pl.edu.pjwsk.MPR_Spring_2.service.StringUtilsService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MprSpring2ApplicationTests {
	@Mock
	private CatRepository repository;
	@InjectMocks
	private CatService catService;

	private final StringUtilsService stringUtilsService = new StringUtilsService(null);
	void setUp(){
		MockitoAnnotations.openMocks(this);
		when(repository.findAll()).thenReturn(Arrays.asList(
				new Cat("Meszek", "Brown"),
				new Cat("Buba", "Black"),
				new Cat("Ivan", "White")
		));

	}


	@Test
	public void shouldGetCatByName() {
		String name = "Meszek";
		List<Cat> expectedCats = List.of(new Cat("Meszek", "Brown"));

		when(repository.findByName(name)).thenReturn(expectedCats);

		List<Cat> result = catService.getByName(name);

		assertEquals(expectedCats, result);
		verify(repository, times(1)).findByName(name);
	}

	@Test
	public void shouldGetCatByColor(){
		String color = "Brown";
		List<Cat> expectedCats = List.of(new Cat("Meszek", "Brown"));

		when(repository.findByColor(color)).thenReturn(expectedCats);

		List<Cat> result = catService.getByColor(color);

		assertEquals(expectedCats, result);
		verify(repository, times(1)).findByColor(color);
	} //sprawdza czy getByColor zwraca liste kotow o danym kolorze, gdy taki kolor jest obecny

	@Test
	public void shouldReturnCatList(){
		List<Cat> result = catService.getCatList();

		assertEquals(3, result.size());
		verify(repository, times(1)).findAll();
	} // sprawdza metodę getCatList, która powinna zwrócić całą listę kotów.
	// Sprawdzamy, czy zwrócono listę o rozmiarze 3 (zgodnie z wartościami dodanymi w setUp)
	// i czy metoda findAll została wywołana raz.

	@Test
	public void shouldAddCat(){
		Cat newCat = new Cat("Bear", "Grey");

		catService.add(newCat);
		verify(repository, times(1)).save(newCat);
	} //czy add wywołuje save w repozytorium, gdy dodajemy nowego kota.
	// Test weryfikuje, czy metoda zapisu została wywołana dokładnie raz z nowym kotem

	@Test
	public void shouldGetCatByIdWhenExists(){
		Cat expectedCat = new Cat("Buba", "Black");
		when(repository.findById(1L)).thenReturn(Optional.of(expectedCat));

		Cat result = catService.getCatById(1L);

		assertNotNull(result);
		assertEquals(expectedCat, result);
		verify(repository, times(1)).findById(1L);
	} //prawdza metodę getCat, która zwraca kota na podstawie ID.
	// Ustawiamy mock tak, by zwracał kota dla ID 1L,
	// a następnie sprawdzamy, czy wynik jest zgodny z oczekiwaniami.

	@Test
	public void shouldReturnNullWhenCatNotFoundById(){
		when(repository.findById(1L)).thenReturn(Optional.empty());

		Cat result = catService.getCatById(1L);

		assertNull(result);
		verify(repository, times(1)).findById(1L);
	} //czy getCat zwraca null, gdy kota o podanym ID nie ma w repozytorium

	@Test
	public void shouldDeleteCatWhenIdExists(){
		when(repository.existsById(1L)).thenReturn(true);

		catService.deleteCat(1L);

		verify(repository, times(1)).deleteById(1L);
	} //sprawdza, czy deleteCat usuwa kota, gdy ID istnieje,
	// oraz czy metoda deleteById jest wywołana raz dla danego ID

	@Test
	public void shouldUpdateCatWhenIdExists(){
		Cat existingCat = new Cat("Buba", "Black");
		Cat updateCat = new Cat("Teddy", "White");

		when(repository.existsById(1L)).thenReturn(true);
		when(repository.findById(1L)).thenReturn(Optional.of(existingCat));

		catService.updateCat(1L, updateCat);

		assertEquals("Teddy", existingCat.getName());
		assertEquals("White", existingCat.getColor());
		verify(repository, times(2)).save(existingCat);
	} //czy updateCat aktualizuje dane kota o podanym ID, gdy taki kot istnieje,
	// oraz czy save jest wywołane z zaktualizowanymi danymi kota

	@Test
	public void shouldReturnNullWhenUpperCaseInputIsNull(){
		String result = stringUtilsService.upper(null);

		assertNull(result);
	} //czy upper zwraca null, gdy przekazano null jako argument

	@Test
	public void shouldConvertToUpperCaseWhenInputIsNotNull(){
		String input = "misia";
		String result = stringUtilsService.upper(input);

		assertEquals("MISIA", result);
	} //czy metoda upper zamienia małe litery na wielkie dla ciągu znaków

	@Test
	public void shouldConvertToLowerWithCapitalFirstLetter(){
		String input = "MISIA";
		String result = stringUtilsService.lower(input);

		assertEquals("Misia", result);
	} // czy lower przekształca wszystkie litery na małe, a pierwszą literę na wielką

	@Test
	public void shouldReturnNullWhenLowerCaseInputIsNull(){
		String result = stringUtilsService.lower(null);

		assertNull(result);
	} //czy lower zwraca null, gdy przekazano null

	@Test
	public void shouldReturnNullWhenLowerCaseIsEmpty(){
		String result = stringUtilsService.lower("");

		assertNull(result);
	} //czy lower zwraca null dla pustego ciągu znaków

}
