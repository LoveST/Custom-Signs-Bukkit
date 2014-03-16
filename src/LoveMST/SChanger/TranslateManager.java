package LoveMST.SChanger;

public class TranslateManager {

	private TranslateManager() {
	}

	private static TranslateManager instance = new TranslateManager();

	public static TranslateManager getInstance() {
		return instance;
	}

	private static String fpath = "langs";
	private static String fName = sChanger.currentLang;

	public String get(String s) {
		if (ConfigManager.contains(s, fpath, fName)) {
			return ConfigManager.getString(s, fpath, fName);
		} else {
			return "#%error$#";
		}
	}

	public static void checkMainLangFile() {
		if (!ConfigManager.checkFolder("langs")) {
			ConfigManager.makeFolder("langs");
		}
		if (!ConfigManager.checkFile(fpath, "english")) {

			ConfigManager.createConfig(fpath, "english");
		}

	}

}
