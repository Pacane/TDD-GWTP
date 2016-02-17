package com.arcbees.client.gin;

import com.arcbees.client.api.ApiPaths;
import com.google.gwt.inject.client.AbstractGinModule;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;

public class DispatchModule extends AbstractGinModule {
    @Override
    protected void configure() {
        RestDispatchAsyncModule.Builder dispatchBuilder =
                new RestDispatchAsyncModule.Builder();
        install(dispatchBuilder.build());

        bindConstant().annotatedWith(RestApplicationPath.class).to("http://localhost:8084" + ApiPaths.ROOT);
    }
}
