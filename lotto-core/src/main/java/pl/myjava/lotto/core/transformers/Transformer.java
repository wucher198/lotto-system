package pl.myjava.lotto.core.transformers;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Transformer<DTO, ENTITY> {	
	abstract public DTO toDTO(ENTITY entity);
	abstract public ENTITY toENTITY(DTO dto);
	
	public List<DTO> toDTOs(List<ENTITY> entities) {
		return entities.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public List<ENTITY> toENTITYs(List<DTO> dtos) {
		return dtos.stream().map(this::toENTITY).collect(Collectors.toList());
	}
}
