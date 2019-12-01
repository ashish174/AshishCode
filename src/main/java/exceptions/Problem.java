package exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.internal.NotNull;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
  @NotNull
  private final URI type;
  @NotNull
  private final String title;
  private final URI instance;
  private final Integer status;
  private final String detail;
  private final List<Problem> errors;
  private final Map<String, Object> extensionFields;
  private final String problemId;


  Problem(URI type, String title, URI instance, Integer status, String detail, List<Problem> errors, Map<String, Object> extensions, String problemId) {
    if (type == null) {
      throw new NullPointerException("type may not be null");
    } else if (title == null) {
      throw new NullPointerException("title may not be null");
    } else {
      this.type = type;
      this.title = title;
      this.instance = instance;
      if (status != null && (status < 100 || status >= 600)) {
        throw new IllegalArgumentException("status must be between 100 and 599");
      } else {
        this.status = status;
        this.detail = detail;
        this.errors = errors;
        this.extensionFields = extensions;
        this.problemId = problemId == null ? UUID.randomUUID().toString() : problemId;
      }
    }
  }
}