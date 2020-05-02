package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {

        SchoolBook[] newArray = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, newArray, 0, schoolBooks.length);
        newArray[newArray.length - 1] = book;

        schoolBooks = newArray;

        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {

        SchoolBook[] result = new SchoolBook[0];

        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                SchoolBook[] newResult = new SchoolBook[result.length + 1];
                System.arraycopy(result, 0, newResult, 0, result.length);
                newResult[newResult.length - 1] = schoolBook;

                result = newResult;
            }
        }

        return result;
    }

    @Override
    public boolean removeByName(String name) {

        SchoolBook[] forRemove = findByName(name);

        if (forRemove.length == 0){
            return false;
        }

        SchoolBook[] resultArray = new SchoolBook[schoolBooks.length - forRemove.length];
        int resultArrayIndex = 0;

        for (SchoolBook schoolBook : schoolBooks) {
            if (!schoolBook.getName().equals(name)) {
                resultArray[resultArrayIndex] = schoolBook;
                resultArrayIndex++;
            }
        }

        schoolBooks = resultArray;

        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
