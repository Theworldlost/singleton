/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: EPL-2.0
 */
package com.vmware.vip.i18n.api.v2.translation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.vip.api.rest.API;
import com.vmware.vip.api.rest.APIOperation;
import com.vmware.vip.api.rest.APIParamName;
import com.vmware.vip.api.rest.APIParamValue;
import com.vmware.vip.api.rest.APIV2;
import com.vmware.vip.common.i18n.dto.response.APIResponseDTO;
import com.vmware.vip.core.messages.exception.L3APIException;
import com.vmware.vip.i18n.api.base.TranslationSourceAction;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Provide RESTful API for product to get translation by String base.
 *
 */
@RestController("v2-TranslationSourceAPI")
public class TranslationSourceAPI extends TranslationSourceAction {

	/**
	 * Provide translation based on String
	 *
	 * @param productName
	 *        The name of product.
	 * @param component
	 *        The name of component. 
	 * @param version
	 *        The release version of product.
	 * @param source
	 *        The English string that you want to translate.
	 * @param locale
	 *        The name of locale. e.g: ja_JP, zh_CN.
	 * @param request
	 *        Extends the ServletRequest interface to provide request information for HTTP servlets.
     * @return APIResponseDTO
     *         The object which represents response status.
	 */
    @ApiOperation(value = APIOperation.SOURCE_TRANSLATION_GET_VALUE, notes = APIOperation.KEY_TRANSLATION_GET_NOTES)
	@RequestMapping(value = APIV2.SOURCE_TRANSLATION_GET, method = RequestMethod.GET, produces = { API.API_CHARSET })
    public APIResponseDTO getTranslationBySource (
            @PathVariable(APIParamName.PRODUCT_NAME) String productName,
            @PathVariable(APIParamName.COMPONENT) String component,
            @PathVariable(value = APIParamName.VERSION) String version,
            @PathVariable(value = APIParamName.LOCALE, required = true) String locale,
            @PathVariable(value = APIParamName.SOURCE, required = true) String source,
            @RequestParam(value = APIParamName.SOURCE_FORMAT, required = false) String sourceFormat,
            @ApiParam(name = APIParamName.PSEUDO, value = APIParamValue.PSEUDO)
            @RequestParam(value = APIParamName.PSEUDO, required=false, defaultValue="false") String pseudo,
            HttpServletRequest request) throws L3APIException{
        return super.getTranslationBySource(productName, component, version, locale, source, sourceFormat, pseudo, request);
	}

	/**
	 * Create source with post data, especially it's used for creating long
	 * source
	 * 
	 * @param productName
	 *        The name of product.
	 * @param component
	 *        The name of component.
	 * @param version
	 *        The release version of product.
	 * @param source
	 *        The English string that you want to translate.
	 * @param locale
	 *        The name of locale. e.g: ja_JP, zh_CN.
	 * @param request
	 *        Extends the ServletRequest interface to provide request information for HTTP servlets.
	 * @return APIResponseDTO The object which represents response status.
	 */
    @Deprecated
    @ApiOperation(value = APIOperation.SOURCE_TRANSLATION_POST_VALUE, notes = APIOperation.SOURCE_TRANSLATION_POST_NOTES)
    @RequestMapping(value = APIV2.SOURCE_TRANSLATION_POST, method = RequestMethod.POST, produces = { API.API_CHARSET })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
	public APIResponseDTO createSource (
			@PathVariable(APIParamName.PRODUCT_NAME) String productName,
			@PathVariable(APIParamName.COMPONENT) String component,
			@PathVariable(value = APIParamName.VERSION) String version,
			@PathVariable(value = APIParamName.LOCALE) String locale,
            @RequestBody String source,
            @RequestParam(value = APIParamName.SOURCE_FORMAT, required = false) String sourceFormat,
            @ApiParam(name = APIParamName.COLLECT_SOURCE, value = APIParamValue.COLLECT_SOURCE)
            @RequestParam(value = APIParamName.COLLECT_SOURCE, required=false, defaultValue="false") String collectSource,
            @ApiParam(name = APIParamName.PSEUDO, value = APIParamValue.PSEUDO)
            @RequestParam(value = APIParamName.PSEUDO, required=false, defaultValue="false") String pseudo,
            HttpServletRequest request)  throws L3APIException {
		return super.createSource(productName, component, version, locale, source, sourceFormat, collectSource, pseudo, request);
	}
}
