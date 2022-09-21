package com.umldesigner.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    public static final String dbLocation = "jdbc:mysql://localhost:3306/uml_designer_db";
    public static final String dbUname = "root";
    public static final String dbPass = "eW#n2$8g9%S7VHu*";

    // public static String dbLocation =
    // "jdbc:mysql://bugtracker_database/bugtracker_db?useSSL=false";
    // public static String dbUname = "user1";

    /*
     * @Bean
     * ServletRegistrationBean h2ServletRegistration() {
     * ServletRegistrationBean registrationBean =
     * new ServletRegistrationBean(new WebServlet());
     * registrationBean.addUrlMappings("/h2/*");
     * return registrationBean;
     * }
     */

    /*
     * logging.level.org.hibernate.SQL=DEBUG
     * logging.level.org.hibernate.type=TRACE
     * 
     */
}