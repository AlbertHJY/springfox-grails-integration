package springfox.documentation.grails;

import com.fasterxml.classmate.TypeResolver;
import grails.core.GrailsDomainClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class UpdateActionSpecificationFactory implements ActionSpecificationFactory {
  private final TypeResolver resolver;

  @Autowired
  public UpdateActionSpecificationFactory(TypeResolver resolver) {
    this.resolver = resolver;
  }

  @Override
  public ActionSpecification create(GrailsDomainClass domain) {
    return new ActionSpecification(
        new HashSet<>(Arrays.asList(RequestMethod.POST, RequestMethod.PUT)),
        new HashSet<>(Collections.singletonList(MediaType.APPLICATION_JSON)),
        new HashSet<>(Collections.singletonList(MediaType.APPLICATION_JSON)),
        new ArrayList<>(Arrays.asList(
            pathParameter(1, "id", resolver.resolve(idType(domain))),
            bodyParameter(resolver.resolve(domain.getClazz())))),
        resolver.resolve(domain.getClazz()));

  }
}