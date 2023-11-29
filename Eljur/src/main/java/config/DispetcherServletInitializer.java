package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import jakarta.servlet.Filter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import org.springframework.web.filter.HiddenHttpMethodFilter;

public class DispetcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	public Class<?>[] getRootConfigClasses() {
		return null;
	}
	@Override
	public Class<?>[] getServletConfigClasses() {
		return new Class[]{SpringConfig.class};
	}
	@Override
	public String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	 @Override
	    public void onStartup(ServletContext container) throws ServletException {
	        super.onStartup(container);
	        registerHiddenFieldFilter(container);
	    }
	
	private void registerHiddenFieldFilter(ServletContext container) {
    	container.addFilter("hiddenHttpMethodFilter",
                (Filter) new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
}
