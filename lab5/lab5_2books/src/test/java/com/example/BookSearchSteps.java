package com.example;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import org.slf4j.Logger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



import static org.junit.jupiter.api.Assertions.*;

public class BookSearchSteps {
    static final Logger log = getLogger(lookup().lookupClass());
    Library library = new Library();
    List<Book> books_found_list = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDate iso8601Date(String year, String month, String day){
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    @Given("I have the following books in the store")
    public void setup(DataTable books) {
        List<Map<String, String>> rows = books.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            library.addBook(new Book(columns.get("title"), columns.get("author"), LocalDate.parse( columns.get("published") ) ));
        }
    }

    @Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
    public void add_new_book(final String title, final String author, final LocalDate ldt) {
        Book book = new Book(title, author, ldt);
        library.addBook(book);
    }

    @Then("a book with the title {string}, written by {string}, published in {iso8601Date} is removed")
    public void removeBook(final String title, final String author, final LocalDate ldt) {
        Book book = new Book(title, author, ldt);
        library.removeBook(book);
    }

    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void searchBooksByYear(LocalDate fromYear, LocalDate toYear) {
        log.info("Searching for books published between {} and {}", fromYear, toYear);
        books_found_list = library.findBooksBetweenYears(fromYear, toYear);
    }

    @When("the customer searches for books written by {string}")
    public void searchBooksByAuthor(String author) {
        log.info("Searching for books written by {}", author);
        books_found_list = library.findBooksByAuthor(author);
    }


    @When("the customer searches for books with the title {string}")
    public void searchBooksByTitle(String title) {
        log.info("Searching for books with the title {}", title);
        books_found_list = library.findBooksByTitle(title);
    }

    @Then("{int} books should have been found")
    public void verifyResultSize(int num) {
        assertEquals(books_found_list.size(), num);
    }
}