package pl.myjava.lotto.core.transformers;

public class TransformerFactory {
	public static LottoGameTransformer getLottoGameTransformer() {
		return new LottoGameTransformer();
	}
	
	public static LottoNumberTransformer getLottoNumberTransformer() {
		return new LottoNumberTransformer();
	}
}
