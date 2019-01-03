package com.handler;

import com.model.basic.flow.BasicRequest;
import com.model.basic.flow.BasicResponse;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class BasicHandler extends SpringBootRequestHandler<BasicRequest, BasicResponse> {
}
