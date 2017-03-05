package by.htp.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Game {

	Character lastLetterLastWord = null;
	Set<String> gameSet = new HashSet<>();
	Map<Character, Set<String>> cityMap;

	public Game() {
		Set<String> citiesSet = readCitiesFromFile();
		cityMap = createCityMap(citiesSet);
		System.out.println();
	}

	public Character getLastLetterLastWord() {
		return lastLetterLastWord;
	}

	public void setLastLetterLastWord(Character lastLetterLastWord) {
		this.lastLetterLastWord = lastLetterLastWord;
	}

	public Set<String> getGameSet() {
		return gameSet;
	}

	public void setGameSet(Set<String> gameSet) {
		this.gameSet = gameSet;
	}

	public Map<Character, Set<String>> getCityMap() {
		return cityMap;
	}

	public void setCityMap(Map<Character, Set<String>> cityMap) {
		this.cityMap = cityMap;
	}

	private Set<String> readCitiesFromFile() {
		Set<String> result = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(getClass().getClassLoader().getResource("cities.txt").getFile()))))) {
			String city;
			while ((city = br.readLine()) != null) {
				result.add(city.toUpperCase());
			}
		} catch (IOException e) {
			System.out.println("Reading: there is problem with data file (dao layer)");
		}
		return result;
	}

	public static Character getFirstChar(String city) {
		return city.charAt(0);
	}

	public static Character getLastChar(String city) {
		int pos = city.length() - 1;
		char lastChar = city.toUpperCase().charAt(pos);
		if (city.toUpperCase().charAt(pos) == 'Й') {
			return 'И';
		} else if (lastChar == 'Ь' || lastChar == 'Ы' || lastChar == 'Ъ') {
			pos--;
		}
		return city.toUpperCase().charAt(pos);
	}

	private Map<Character, Set<String>> createCityMap(Set<String> cities) {
		Map<Character, Set<String>> cityMap = new HashMap<Character, Set<String>>();
		for (String city : cities) {
			Set<String> cs = cityMap.get(getFirstChar(city));
			if (cs == null) {
				cityMap.put(getFirstChar(city), cs = new HashSet<String>());
			}
			cs.add(city);
		}
		return cityMap;
	}

	public String returnCityNameByLastLetterFromMap() {
		String cityName = null;
		Set<String> citySet = cityMap.get(lastLetterLastWord);
		if (citySet != null) {
			for (String city : citySet) {
				if (checkUniqueness(city)) {
					cityName = city;
					break;
				}
			}
		}
		return cityName;
	}

	public boolean inputWordCorrect(Character firstLetterCityName) {
		return getGameSet().isEmpty()
				|| (!getGameSet().isEmpty() && firstLetterCityName.equals(getLastLetterLastWord()));
	}

	public boolean checkUniqueness(String inputWord) {
		return !gameSet.contains(inputWord);
	}

	public static String readCityNameUserImput() {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		return str.toUpperCase();
	}
}
