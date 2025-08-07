package com.log.dev.api.exceptions;

public class ArticleNotFoundException extends HttpException {

    public ArticleNotFoundException() {
        super(404, "Article not found");
    }

    public ArticleNotFoundException(String message) {
        super(404, message);
    }

}