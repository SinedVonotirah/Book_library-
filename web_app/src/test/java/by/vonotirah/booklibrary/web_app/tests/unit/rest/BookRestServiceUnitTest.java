package by.vonotirah.booklibrary.web_app.tests.unit.rest;

import static org.mockito.Mockito.verify;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import by.vonotirah.booklibrary.persistence.BookDao;
import by.vonotirah.booklibrary.persistence.domain.Book;
import by.vonotirah.booklibrary.persistence.domain.User;
import by.vonotirah.booklibrary.web_app.BookWebService;
import by.vonotirah.booklibrary.web_app.rest.BookRestService;
import by.vonotirah.booklibrary.web_app.rest.DaoManagerRestService;
import by.vonotirah.booklibrary.web_app.tests.AbstractTest;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DaoManagerRestService.class)
public class BookRestServiceUnitTest extends AbstractTest {

	private BookDao mockedBookDao;

	@Before
	public void setUp() {
		PowerMockito.mockStatic(DaoManagerRestService.class);
		mockedBookDao = Mockito.mock(BookDao.class);
		PowerMockito.when(DaoManagerRestService.getBookDao()).thenReturn(mockedBookDao);
	}

	@Test
	public void createBookSoapUnitTest() throws SQLException {
		BookWebService bookService = new BookRestService();
		Book book = getRandomBookObject();
		bookService.createBook(book);

		verify(mockedBookDao).createBook(book);
	}

	@Test
	public void updateBookSoapUnitTest() throws SQLException {
		BookWebService bookService = new BookRestService();
		Book book = getRandomBookObject();
		bookService.updateBook(book);

		verify(mockedBookDao).updateBook(book);
	}

	@Test
	public void assignBookSoapUnitTest() throws SQLException {
		BookWebService bookService = new BookRestService();
		Book book = getRandomBookObject();
		User user = getRandomUserObject();
		bookService.assignBook(book, user);

		verify(mockedBookDao).assignBook(book, user);

	}

	@Test
	public void getBookByIdSoapUnitTest() throws SQLException {
		BookWebService bookService = new BookRestService();
		bookService.getBookById(Mockito.anyString());

		verify(mockedBookDao).getBookById(Mockito.anyString());
	}

	@Test
	public void getBookByNameSoapUnitTest() throws SQLException {
		BookWebService bookService = new BookRestService();
		bookService.getBookByName(Mockito.anyString());

		verify(mockedBookDao).getBookByName(Mockito.anyString());
	}

	@Test
	public void passBookSoapUnitTest() throws SQLException {
		BookWebService bookService = new BookRestService();
		Book book = getRandomBookObject();
		bookService.passBook(book);

		verify(mockedBookDao).passBook(book);
	}

	// @Test
	public void getAllBooksSoapUnitTest() throws SQLException {
		BookWebService bookService = new BookRestService();
		bookService.getAllBooks();

		verify(mockedBookDao).getAllBooks();
	}

	// @Test
	public void getAllFreeBooksSoapUnitTest() throws SQLException {
		BookWebService bookService = new BookRestService();
		bookService.getAllFreeBooks();

		verify(mockedBookDao).getAllFreeBooks();
	}

	@Test
	public void deleteBookSoapUnitTest() throws SQLException {
		BookWebService bookService = new BookRestService();
		Book book = getRandomBookObject();
		bookService.deleteBook(book);

		verify(mockedBookDao).deleteBook(book);
	}
}