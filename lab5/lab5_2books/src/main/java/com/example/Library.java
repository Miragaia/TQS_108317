package com.example;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
 
public class Library {
	private List<Book> booksDB = new ArrayList<>();
 
	public void addBook(final Book book) {
		booksDB.add(book);
	}
 
	public List<Book> findBooksBetweenYears(LocalDate fromYear, LocalDate toYear) {
        List<Book> foundedBooks = new ArrayList<>();

        for (Book book : booksDB) {
            System.out.println(book.getPublished() + " " + fromYear + " " + toYear);
            System.out.println(book.getPublished().isAfter(fromYear) + " " + book.getPublished().isBefore(toYear));
            if (book.getPublished().isAfter(fromYear) && book.getPublished().isBefore(toYear)) foundedBooks.add(book);
            
        }

        return foundedBooks;
    }

    public List<Book> findBooksByTitle(String title) {
        return booksDB.stream().filter( book -> {return title.equals(book.getTitle());}).sorted(Comparator.comparing(Book::getTitle).reversed()).collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String author) {
        return booksDB.stream().filter( book -> {return author.equals(book.getAuthor());}).sorted(Comparator.comparing(Book::getAuthor).reversed()).collect(Collectors.toList());
    }

    public void setBooks(List<Book> booksDB) {
        this.booksDB = booksDB;
    }

    public List<Book> getBooks() {
        return booksDB;
    }


    public void removeBook(Book book) {
        try {
            booksDB.remove(book);
        }catch (Exception NullPointerException){
            System.out.println("The book does no exists");
        }

    }
}