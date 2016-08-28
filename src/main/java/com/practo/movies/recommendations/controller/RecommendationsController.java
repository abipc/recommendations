package com.practo.movies.recommendations.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practo.movies.recommendations.manager.RecommendationsManager;
import com.practo.movies.recommendations.models.request.MovieReccDataModel;
import com.practo.movies.recommendations.models.request.RatingUpsertRequestModel;
import com.practo.movies.recommendations.models.request.RatingsSearchRequestModel;
import com.practo.movies.recommendations.utils.ObjMapperFactory;

@Controller
@RequestMapping("/")
public class RecommendationsController {
	/**
	 * 
	 * Viewed Also Viewed 
	 * 
	 * HTTP GET
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/recommendations", method = RequestMethod.GET)
    public @ResponseBody String getMovieRecommendations(HttpServletRequest request,
    		HttpServletResponse response) throws Exception {    	
    	String user = request.getParameter("user");
    	Map<String, Object> data = RecommendationsManager.getMovieRecommendations(user);  		
    	return ObjMapperFactory.getRecommendationsResponse(data, user);
    }
    
    /**
     * 
     * Viewed Also Viewed
     * 
     * HTTP POST
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/recommendations", method = RequestMethod.POST)
    public @ResponseBody String getRecommendations(@RequestBody MovieReccDataModel 
    		requestData) throws Exception {    	
    	Map<String, Object> data = RecommendationsManager.getRecommendations(requestData);  		
    	return ObjMapperFactory.getRecommendationsResponse(data);
    }  
    
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/ratings", method = RequestMethod.GET)
    public @ResponseBody String getMovieDetails(HttpServletRequest request,
    		HttpServletResponse response) throws Exception {
    	Map<String, Object> params = ObjMapperFactory.getQueryParams(request);
    	Map<String, Object> data = RecommendationsManager.searchRatings(params);  		
    	return ObjMapperFactory.getRatingsResponse(data);
    }
    
    /**
     * 
     * @param searchMovie
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/ratings/search", method = RequestMethod.POST)
    public @ResponseBody String searchMovie(@RequestBody RatingsSearchRequestModel 
    		requestModel)  throws Exception {
    	Map<String, Object> data = RecommendationsManager.searchRatings(requestModel);  		
    	return ObjMapperFactory.getRatingsResponse(data);
    }    
   
    /**
     * 
     * @param data
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/ratings", method = RequestMethod.POST)
    public @ResponseBody String upsertMovie(@RequestBody RatingUpsertRequestModel data)  
    		throws Exception{
		RecommendationsManager.upsertRating(data);
    	return ObjMapperFactory.getSuccessData();
    }
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ratings", method = RequestMethod.DELETE)
    public @ResponseBody String deleteMovie(@RequestBody List<String> data)  throws Exception{
		RecommendationsManager.deleteRating(data);
    	return ObjMapperFactory.getSuccessData();
    }	    
      

}
