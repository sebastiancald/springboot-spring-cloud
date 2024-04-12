package com.jhonsc.springbootapp.gateway.filter.factory;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;
/**
 * Clase para filtros personalizados para el gateway, ademas de los que tiene por default.
 * @author jhoncalderon
 *
 */
@Component
public class ExampleGatewayFilterFactory extends AbstractGatewayFilterFactory<ExampleGatewayFilterFactory.Configuration> {

	private final Logger log = LoggerFactory.getLogger(ExampleGatewayFilterFactory.class);
	

	public ExampleGatewayFilterFactory() {
		super(Configuration.class);
	}

	@Override
	public GatewayFilter apply(Configuration config) {
		// TODO Auto-generated method stub
		return (exchabge, chain)->{
			log.info("Ejecutando pre de gateway filter factory: ".concat(config.mensaje));
			return chain.filter(exchabge).then(Mono.fromRunnable(()->{
				
				Optional.ofNullable(config.cookieValor).ifPresent(cookie -> {
					exchabge.getResponse().addCookie(ResponseCookie.from(config.cookieNombre, cookie).build());
				});
				
				log.info("Ejecutando post de gateway filter factory: ".concat(config.mensaje));
				
			}));
		};
	}
	
public static class Configuration {
		
		private String mensaje;
		private String cookieValor;
		private String cookieNombre;
		public String getMensaje() {
			return mensaje;
		}
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		public String getCookieValor() {
			return cookieValor;
		}
		public void setCookieValor(String cookieValor) {
			this.cookieValor = cookieValor;
		}
		public String getCookieNombre() {
			return cookieNombre;
		}
		public void setCookieNombre(String cookieNombre) {
			this.cookieNombre = cookieNombre;
		}

	}

}
