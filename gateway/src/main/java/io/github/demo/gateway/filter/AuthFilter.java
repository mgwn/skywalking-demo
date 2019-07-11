package io.github.demo.gateway.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends ZuulFilter {
  private static final String TOKEN_KEY = "token";

  @Override
  public String filterType() {
    return PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {
    RequestContext ctx = RequestContext.getCurrentContext();
    if (StringUtils.isBlank(ctx.getRequest().getHeader(TOKEN_KEY))) {
      ctx.setSendZuulResponse(false);
      ctx.setResponseStatusCode(401);
      return null;
    }
    return null;
  }
}
