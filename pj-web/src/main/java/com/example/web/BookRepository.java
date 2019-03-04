package com.example.web;

import com.example.db.jooq.gen.tables.JBook;
import com.example.db.jooq.gen.tables.pojos.BookVo;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class BookRepository {

    private final DSLContext dslContext;

    @Autowired
    public BookRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<Book> selectAll() {

        final JBook jBook = JBook.BOOK;

        final List<BookVo> selected = dslContext
                .select(jBook.ISBN, jBook.PUBLISH_DATE, jBook.PUBLISH_DATE)
                .from(jBook)
                .orderBy(jBook.PUBLISH_DATE)
                .fetchInto(BookVo.class);

        return null; // TODO
    }
}
