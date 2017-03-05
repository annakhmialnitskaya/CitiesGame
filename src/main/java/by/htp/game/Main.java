package by.htp.game;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		GameResult result = GameResult.IN_PROGRESS;
		do {
			System.out.println("Введите название города:");
			String cityNameUserInput = Game.readCityNameUserImput();
			Character firstLetterCityName = Game.getFirstChar(cityNameUserInput);
			if ("Q".equals(cityNameUserInput)) {
				result = GameResult.COMP_WINS;
			} else {
				if (game.inputWordCorrect(firstLetterCityName)) {
					if (game.checkUniqueness(cityNameUserInput)) {
						game.getGameSet().add(cityNameUserInput);
						game.setLastLetterLastWord(Game.getLastChar(cityNameUserInput));
						String compCity = game.returnCityNameByLastLetterFromMap();
						if (compCity != null) {
							game.getGameSet().add(compCity);
							game.setLastLetterLastWord(Game.getLastChar(compCity));
							System.out.println(compCity);
						} else {
							result = GameResult.USER_WINS;
						}
					} else {
						System.out.println("Слово не уникально!");
					}
				} else {
					System.out.println("Слово не подходит!");
				}
			}
		} while (result.equals(GameResult.IN_PROGRESS));
		System.out.println(result);
	}
}
