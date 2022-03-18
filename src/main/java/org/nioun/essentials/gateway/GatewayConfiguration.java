package org.nioun.essentials.gateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

	@Bean
 public RouteLocator	gatewayRouter(RouteLocatorBuilder builder){
		
		return builder.routes()
				.route(p->p.path("/get")
					.filters(f->f
							.addRequestHeader("MyHeader", "MyURI")
							.addRequestParameter("Param", "MyValue"))
					.uri("http://httpbin.org:80"))
					
				    .route(p->p.path("fournisseur/**")
								.uri("lb://fournisseur"))
					
					.route(p->p.path("/produit/**")
							.uri("lb://produit"))
					
					.route(p->p.path("/commande/**")
								.uri("lb://commande"))
					
					.route(p->p.path("/commande-feign/**")
							.uri("lb://commande"))
					
					.route(p->p.path("/commande-new/**")
							.filters(f->f.rewritePath(
									"/commande-new/(?<segment>.*)",
									"/commande-feign/${segment}"))
							.uri("lb://commande"))
					
					.route(p->p.path("/client/**")
								.uri("lb://client"))
					
					.route(p->p.path("/paiement/**")
								.uri("lb://paiement"))
					
					.route(p->p.path("/point/**")
								.uri("lb://point"))
					
					.route(p->p.path("/livreur/**")
							    .uri("lb://livreur"))
				
					.route(p->p.path("/prestasante/**")
							.uri("lb://prestasante"))
				
				.route(p->p.path("/assurance/**")
							.uri("lb://assurance"))
				
				.route(p->p.path("/tontine/**")
						    .uri("lb://tontine"))
			
					
					.build() ;
	}
}
