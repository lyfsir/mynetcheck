package com.lyf.gateway.config;

import com.lyf.jwt.config.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @title: AuthorizeFilter
 * @projectName: xm
 * @description: TODO
 * @author: Zack_Tzh
 * @date: 2019/12/10  14:46
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1. 获取请求
        ServerHttpRequest request = exchange.getRequest();
        //2. 则获取响应
        ServerHttpResponse response = exchange.getResponse();
        //3. 需要拦截的接口
        if (
            request.getURI().getPath().contains("api/food/food/foodinfo/save/toaud") ||
                    request.getURI().getPath().contains("api/topic/topic/topicinfo/save/topic") ||
                    request.getURI().getPath().contains("api/ums/ums/umsmember/loginout")
        ) {
            //4. 获取请求头
            HttpHeaders headers = request.getHeaders();
            //5. 请求头中获取令牌
            String token = headers.getFirst("Authorization");
            //6. 判断请求头中是否有令牌
            if (StringUtils.isEmpty(token)) {
                //7. 响应中放入返回的状态吗, 没有权限访问
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //8. 返回
//            throw new JwtException();
                return response.setComplete();
            }
            //9. 如果请求头中有令牌则解析令牌
            try {
                JwtUtil.parseJWT(token);
            } catch (Exception e) {
                e.printStackTrace();
                //10. 解析jwt令牌出错, 说明令牌过期或者伪造等不合法情况出现
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //11. 返回
                return response.setComplete();
            }
            //12. 放行
            return chain.filter(exchange);
        }
        return chain.filter(exchange);
    }



    @Override
    public int getOrder() {
        return 0;
    }
}

