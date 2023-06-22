#ifndef PLAYER_H
#define PLAYER_H
#include<vector>
#include "Bullet.h"
#include "SFML/Graphics.hpp"

class Player {
private:
	sf::Vector2f position; 
	sf::Vector2f velocity; 
	int health; 
	sf::Sprite sprite; 
public:
	
	Player(const sf::Vector2f& position, const sf::Texture& texture); 
	~Player(); 
	void move(const sf::Vector2f& offset); 
	void draw(sf::RenderWindow& window);
	void handleCollision(); 
	void update(sf::RenderWindow& window);
	sf::FloatRect getBounds() const;
	sf::Vector2f getPosition() const;
	int getHealth(); 
	void setHealth(int Health);
	void fire(std::vector<Bullet>& bullets, const sf::Texture& bulletTexture); 
	int lives = 3;
};



#endif // !PLAYER_H
