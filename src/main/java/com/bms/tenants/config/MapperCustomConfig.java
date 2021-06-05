package com.bms.tenants.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.extensions.spring.SpringMapperConfig;
import org.mapstruct.extensions.spring.example.adapter.MyAdapter;

@MapperConfig(componentModel = "spring", uses = MyAdapter.class)
@SpringMapperConfig(conversionServiceAdapterPackage = "org.mapstruct.extensions.spring.example.adapter", conversionServiceAdapterClassName = "MyAdapter")
public interface MapperCustomConfig {
}