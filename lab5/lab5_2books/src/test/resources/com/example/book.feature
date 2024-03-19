Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Scenario: Search books by publication year
    Given I have the following books in the store
    | title                                | author         |  published |
    | One good book                        | Anonymous      |  2013-03-14|
    | Some other book                      | Tim Tomson     |  2014-08-23|
    | How to cook a dino                   | Fred Flintstone|  2012-01-01|
    When the customer searches for books published between 2011-12-31 and 2014-01-01
    Then 2 books should have been found

  Scenario: Search books by title
    Given I have the following books in the store
    | title                                | author         |  published |
    | One good book                        | Anonymous      |  2013-03-14|
    | Some other book                      | Tim Tomson     |  2014-08-23|
    | How to cook a dino                   | Fred Flintstone|  2012-01-01|
    When the customer searches for books with the title 'One good book'
    Then 1 books should have been found

  Scenario: Search books by Author
    Given I have the following books in the store
    | title                                | author         |  published |
    | One good book                        | Anonymous      |  2013-03-14|
    | Some other book                      | Tim Tomson     |  2014-08-23|
    | How to cook a dino                   | Fred Flintstone|  2012-01-01|
    When the customer searches for books written by 'Tim Tomson'
    Then 1 books should have been found

  Scenario: No books found
    Given I have the following books in the store
    | title                                | author         |  published |
    | One good book                        | Anonymous      |  2013-03-14|
    | Some other book                      | Tim Tomson     |  2014-08-23|
    | How to cook a dino                   | Fred Flintstone|  2012-01-01|
    When the customer searches for books written by 'Darwin'
    Then 0 books should have been found