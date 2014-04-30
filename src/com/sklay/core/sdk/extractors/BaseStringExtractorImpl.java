package com.sklay.core.sdk.extractors;

import java.util.HashMap;
import java.util.Map;

import com.sklay.core.sdk.exceptions.OAuthParametersMissingException;
import com.sklay.core.sdk.model.OAuthRequest;
import com.sklay.core.sdk.utils.MapUtils;
import com.sklay.core.sdk.utils.Preconditions;
import com.sklay.core.sdk.utils.URLUtils;

/**
 * Default implementation of {@link BaseStringExtractor}. Conforms to OAuth 1.0a
 * 
 * @author Pablo Fernandez
 *
 */
public class BaseStringExtractorImpl implements BaseStringExtractor
{

  private static final String AMPERSAND_SEPARATED_STRING = "%s&%s&%s";

  /**
   * {@inheritDoc}
   */
  public String extract(OAuthRequest request)
  {
    checkPreconditions(request);
    String verb = URLUtils.percentEncode(request.getVerb().name());
    String url = URLUtils.percentEncode(request.getSanitizedUrl());
    String params = getSortedAndEncodedParams(request);
    return String.format(AMPERSAND_SEPARATED_STRING, verb, url, params);
  }

  private String getSortedAndEncodedParams(OAuthRequest request)
  {
    Map<String, String> params = new HashMap<String, String>();
    MapUtils.decodeAndAppendEntries(request.getQueryStringParams(), params);
    MapUtils.decodeAndAppendEntries(request.getBodyParams(), params);
    MapUtils.decodeAndAppendEntries(request.getOauthParameters(), params);
    params = MapUtils.sort(params);
    return URLUtils.percentEncode(URLUtils.concatSortedPercentEncodedParams(params));
  }

  private void checkPreconditions(OAuthRequest request)
  {
    Preconditions.checkNotNull(request, "Cannot extract base string from null object");

    if (request.getOauthParameters() == null || request.getOauthParameters().size() <= 0)
    {
      throw new OAuthParametersMissingException(request);
    }
  }
}
