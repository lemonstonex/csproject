package cs.project.evolt.common;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING) // serialized as string
public enum PlugStatus {
    available,
    unavailable,
    out_of_service;
}
