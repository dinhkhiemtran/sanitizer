package com.khiemtran.sanitizer.security;

import org.owasp.encoder.Encode;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.web.util.HtmlUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SanitizerUtils {
  public static String sanitizeString(String untrustedString) {
    return Optional.of(Encode.forHtmlContent(HtmlUtils
        .htmlEscape(untrustedString))).orElse(null);
  }

  public static String sanitizeHTML(String untrustedHTML) {
    PolicyFactory policyFactory = new HtmlPolicyBuilder()
        .allowAttributes("href").onElements("a")
        .allowAttributes("title").globally()
        .allowElements("table", "td", "tbody", "th", "tr", "a", "pre", "figure")
        .allowElements("img")
        .allowAttributes("src").onElements("img")
        .allowAttributes("class").onElements("span", "code", "figure")
        .allowTextIn("span", "pre", "code")
        .allowUrlProtocols("data")
        .allowCommonInlineFormattingElements()
        .allowCommonBlockElements()
        .allowStyling()
        .toFactory();
    return policyFactory.sanitize(untrustedHTML);
  }

  public static List<String> sanitizeStringList(List<String> untrustedListString) {
    return Optional.of(untrustedListString.stream()
        .map(SanitizerUtils::sanitizeString)
        .collect(Collectors.toList())).orElse(null);
  }

  public static String[] sanitizeStringArray(String[] untrustedStringArray) {
    if (untrustedStringArray == null || untrustedStringArray.length == 0) {
      return null;
    }
    return Arrays.stream(untrustedStringArray)
        .map(SanitizerUtils::sanitizeString).toArray(String[]::new);
  }

  public static Path sanitizePathTraversal(String filePath) {
    Path p = Paths.get(filePath);
    return p.toAbsolutePath();
  }

  public static byte[] sanitizeFileContent(byte[] bytes) {
    String base64 = Base64.getEncoder().encodeToString(bytes);
    return Base64.getDecoder().decode(base64);
  }
}
