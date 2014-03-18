package LoveMST.SChanger;

import java.util.HashMap;

public class TranslateManager {

	private TranslateManager() {
	}

	private static TranslateManager instance = new TranslateManager();

	public static TranslateManager getInstance() {
		return instance;
	}

	private static String fpath = "langs";
	private static String fName = sChanger.currentLang;
	private static HashMap<String, String> langs = new HashMap<String, String>();

	public String get(String s) {
		if (langs.containsKey(s)) {
			return langs.get(s);
		} else {
			return "#lang_error_404#";
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

	private static String getFromConfig(String s) {
		if (ConfigManager.contains(s, fpath, fName)) {
			return ConfigManager.getString(s, fpath, fName);
		} else {
			return null;
		}
	}

	public static void setupLangs() {
		if (getFromConfig("t_GetVersion") != null) langs.put("t_GetVersion", getFromConfig("t_GetVersion"));
		if (getFromConfig("t_NoPermission") != null) langs.put("t_NoPermission", getFromConfig("t_NoPermission"));
		if (getFromConfig("t_disabledSignCopy") != null) langs.put("t_disabledSignCopy", getFromConfig("t_disabledSignCopy"));
		if (getFromConfig("t_enabledSignCopy") != null) langs.put("t_enabledSignCopy", getFromConfig("t_enabledSignCopy"));
		if (getFromConfig("t_clickOnTheSignCopy") != null) langs.put("t_clickOnTheSignCopy", getFromConfig("t_clickOnTheSignCopy"));
		if (getFromConfig("t_UnknownCommand") != null) langs.put("t_UnknownCommand", getFromConfig("t_UnknownCommand"));
		if (getFromConfig("t_ConfigReloadComplete") != null) langs.put("t_ConfigReloadComplete", getFromConfig("t_ConfigReloadComplete"));
	
		if (getFromConfig("t_SignCopied") != null) langs.put("t_SignCopied", getFromConfig("t_SignCopied"));
		if (getFromConfig("t_signPasted") != null) langs.put("t_signPasted", getFromConfig("t_signPasted"));
		if (getFromConfig("t_youMostCopySignFirst") != null) langs.put("t_youMostCopySignFirst", getFromConfig("t_youMostCopySignFirst"));
		if (getFromConfig("t_disabledSignCopy") != null) langs.put("t_disabledSignCopy", getFromConfig("t_disabledSignCopy"));
		if (getFromConfig("t_enabledSignCopy") != null) langs.put("t_enabledSignCopy", getFromConfig("t_enabledSignCopy"));
		if (getFromConfig("t_clickOnTheSignCopy") != null) langs.put("t_clickOnTheSignCopy", getFromConfig("t_clickOnTheSignCopy"));
	
		if (getFromConfig("t_disabledSignLineCopy") != null) langs.put("t_disabledSignLineCopy", getFromConfig("t_disabledSignLineCopy"));
		if (getFromConfig("t_mostTypeNumber") != null) langs.put("t_mostTypeNumber", getFromConfig("t_mostTypeNumber"));
		if (getFromConfig("t_wrongNumber14") != null) langs.put("t_wrongNumber14", getFromConfig("t_wrongNumber14"));
		if (getFromConfig("t_enabledSignLineCopy") != null) langs.put("t_enabledSignLineCopy", getFromConfig("t_enabledSignLineCopy"));
		if (getFromConfig("t_clickOnSignToCopyLine") != null) langs.put("t_clickOnSignToCopyLine", getFromConfig("t_clickOnSignToCopyLine"));
		if (getFromConfig("t_mostCopyLineFirst") != null) langs.put("t_mostCopyLineFirst", getFromConfig("t_mostCopyLineFirst"));
		if (getFromConfig("t_linePasted") != null) langs.put("t_linePasted", getFromConfig("t_linePasted"));
		if (getFromConfig("t_lineCopied") != null) langs.put("t_lineCopied", getFromConfig("t_lineCopied"));
		
		if (getFromConfig("t_disabledEditLine") != null) langs.put("t_disabledEditLine", getFromConfig("t_disabledEditLine"));
		if (getFromConfig("t_typeText") != null) langs.put("t_typeText", getFromConfig("t_typeText"));
		if (getFromConfig("t_enabledEditLine") != null) langs.put("t_enabledEditLine", getFromConfig("t_enabledEditLine"));
		if (getFromConfig("t_textAdded") != null) langs.put("t_textAdded", getFromConfig("t_textAdded"));
		if (getFromConfig("t_leftClickSignEdit") != null) langs.put("t_leftClickSignEdit", getFromConfig("t_leftClickSignEdit"));
		if (getFromConfig("t_lineEdited") != null) langs.put("t_lineEdited", getFromConfig("t_lineEdited"));
		if (getFromConfig("t_mostTypeATextFirst") != null) langs.put("t_mostTypeATextFirst", getFromConfig("t_mostTypeATextFirst"));
	}

	public static void reloadLangs() {
		langs.clear();
	}

}
