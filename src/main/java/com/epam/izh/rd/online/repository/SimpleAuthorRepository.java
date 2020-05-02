package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;


public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {

        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] newArray = new Author[authors.length + 1];
            System.arraycopy(authors, 0, newArray, 0, authors.length);
            newArray[newArray.length - 1] = author;
            authors = newArray;
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Author findByFullName(String name, String lastname) {

        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {

        String authorName = author.getName();
        String authorLastName = author.getLastName();

        if (findByFullName(authorName, authorLastName) == null) {
            return false;
        }

        String currentName = "";
        String currentLastName = "";
        Author[] newArray = new Author[authors.length - 1];

        for (int i = 0; i < authors.length; i++) {

            if (authors[i].getName().equals(authorName) && authors[i].getLastName().equals(authorLastName)) {
                System.arraycopy(authors, i, newArray, i, newArray.length - i);
            } else {
                newArray[i] = authors[i];
            }
        }
        authors = newArray;
        return true;
    }


    @Override
    public int count() {
        return authors.length;
    }
}
