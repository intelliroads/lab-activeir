package com.lab.proy.locale;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


@ManagedBean(name="localeBean")
@SessionScoped
public class LocaleBean implements Serializable{

		private Locale currentLocale;	
		private String lang;
		

		private static final long serialVersionUID = 1L;
		 
		private String localeCode;
	 
		
		public LocaleBean(){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle resourceBundle = context.getApplication().getResourceBundle(context, "Messages");
			
			this.currentLocale = resourceBundle.getLocale();
			this.lang = currentLocale.getLanguage();
		}
		
		private static Map<String,String> countries;
		static{
			countries = new LinkedHashMap<String,String>();
			countries.put("English", "en"); //label, value
			countries.put("Spanish", "es");
		}
	 
		public Map<String, String> getCountriesInMap() {
			return countries;
		}
	 
	 
		public String getLocaleCode() {
			return localeCode;
		}
	 
	 
		public void setLocaleCode(String localeCode) {
			this.localeCode = localeCode;
		}
	 
		//value change event listener
		public void countryLocaleCodeChanged(ValueChangeEvent e){
	 
			String newLocaleValue = e.getNewValue().toString();
	 
			//loop country map to compare the locale code
	            for (Map.Entry<String, String> entry : countries.entrySet()) {
	 
	        	   if(entry.getValue().toString().equals(newLocaleValue)){
	 
		        		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(entry.getValue()));
		        		this.currentLocale = new Locale(entry.getValue());
		 
	        	   }
	           }
			}

			public String getLang() {
				return lang;
			}

			public void setLang(String lang) {
				this.lang = lang;
			}

			public Locale getCurrentLocale() {
				return currentLocale;
			}


			public void setCurrentLocale(Locale currentLocale) {
				this.currentLocale = currentLocale;
			}


}
