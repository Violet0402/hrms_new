package com.sumaojin.suzhenping.hrms.vm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonDeserializer;
import com.sumaojin.suzhenping.hrms.jsonUtils.LongJsonSerializer;
import lombok.Data;

@Data
public class DepartmentVM {
    private String label;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long value;
}
