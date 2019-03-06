package com.example.web;

import com.example.db.jooq.gen.tables.JBook;
import com.example.db.jooq.gen.tables.pojos.BookVo;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class BookRepository {

    private final DSLContext dslContext;

    @Autowired
    public BookRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<Book> selectAll() {

        final JBook jBook = JBook.BOOK;

        final List<BookVo> selected = dslContext
                .select(
                        jBook.ISBN,
                        jBook.TITLE,
                        jBook.PUBLISH_DATE
                )
                .from(jBook)
                .orderBy(jBook.PUBLISH_DATE)
                .fetchInto(BookVo.class);
                // .fetchInto(Book.class); // or you can use original class directly !

        return selected
                .stream()
                .map(bookVo -> {
                    Book book = new Book();
                    book.setIsbn(bookVo.getIsbn());
                    book.setTitle(bookVo.getTitle());
                    book.setPublishDate(bookVo.getPublishDate().toLocalDate());
                    return book;
                })
                .collect(Collectors.toList());

        // or you can use Dozer, orika, map struct....
    }
}
