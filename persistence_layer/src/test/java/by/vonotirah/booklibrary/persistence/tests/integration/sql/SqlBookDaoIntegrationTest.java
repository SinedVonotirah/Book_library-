package by.vonotirah.booklibrary.persistence.tests.integration.sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.vonotirah.booklibrary.persistence.BookDao;
import by.vonotirah.booklibrary.persistence.UserDao;
import by.vonotirah.booklibrary.persistence.domain.Book;
import by.vonotirah.booklibrary.persistence.domain.User;
import by.vonotirah.booklibrary.persistence.factory.DaoFactoryContext;
import by.vonotirah.booklibrary.persistence.tests.AbstractTest;

public class SqlBookDaoIntegrationTest extends AbstractTest {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SqlBookDaoIntegrationTest.class);

	private BookDao bookDao;

	@Before
	public void setUp() {
		bookDao = DaoFactoryContext.getFactory("SQL").getBookDao();
	}

	@Test
	public void bookCRUDTest() throws SQLException {
		LOGGER.info("----------------noSqlBookCRUDTest()----------------------");
		LOGGER.info("----------------Create Book----------------------");
		Book book = getRandomBookObject();
		bookDao.createBook(book);
		LOGGER.info("----------------Read Book----------------------");
		Book createdBook = bookDao.getBookByName(book.getName());
		Assert.assertNotNull(createdBook);
		Assert.assertEquals(book.getName(), createdBook.getName());

		LOGGER.info("----------------Update Book----------------------");
		createdBook.setName(randomString("updated name"));
		bookDao.updateBook(createdBook);
		Book updatedBook = bookDao.getBookById(createdBook.getId());
		Assert.assertNotNull(updatedBook);
		Assert.assertEquals(updatedBook.getName(), createdBook.getName());
		Assert.assertNotEquals(updatedBook.getName(), book.getName());

		LOGGER.info("----------------Delete Book----------------------");

		bookDao.deleteBook(updatedBook);
		Assert.assertNull(bookDao.getBookById(updatedBook.getId()));
	}

	@Test
	public void assignBookTest() throws Exception {
		UserDao userDao = DaoFactoryContext.getFactory("SQL").getUserDao();

		Book book = getRandomBookObject();
		User user = getRandomUserObject();

		userDao.createUser(user);
		bookDao.createBook(book);
		Book bookFromDB = bookDao.getBookByName(book.getName());
		User userFromDB = userDao.getUserByLastName(user.getLastName());
		bookDao.assignBook(bookFromDB, userFromDB);

		Book assignedBook = bookDao.getBookByName(book.getName());

		Assert.assertEquals(assignedBook.getUserId(), userFromDB.getId());
	}

}
