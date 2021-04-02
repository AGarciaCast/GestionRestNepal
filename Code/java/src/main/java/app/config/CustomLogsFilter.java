package app.config;

import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class CustomLogsFilter extends AbstractRequestLoggingFilter {

  public CustomLogsFilter() {
    setIncludeQueryString(true);
    setIncludePayload(true);
    setMaxPayloadLength(10_000);
    setIncludeHeaders(false);
    setAfterMessagePrefix("REQUEST BODY : ");
  }

  @Override
  protected boolean shouldLog(HttpServletRequest request) {
    return !request.getRequestURI().contains("actuator");
  }

  @Override
  protected void beforeRequest(HttpServletRequest request, String message) {}

  @Override
  protected void afterRequest(HttpServletRequest request, String message) {
    logger.info(message);
  }
}
