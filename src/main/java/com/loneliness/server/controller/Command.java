package com.loneliness.server.controller;


import java.util.concurrent.LinkedBlockingDeque;

public interface Command<RequestType,ReplyType> {
    ReplyType execute(RequestType request);
}
