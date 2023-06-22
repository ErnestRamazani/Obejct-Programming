#include "Player.h"

Player::Player(const sf::Vector2f& position, const sf::Texture& texture) {
	this->position = position; 
	velocity = sf::Vector2f(0, 0); 
	health = 1000; 
	
	sprite.setTexture(texture); 
	sprite.setPosition(this->position); 
	sprite.setScale(0.12f, 0.12f);

	
}

Player::~Player() {

}

void Player::move(const sf::Vector2f& offset) {
	velocity = offset; 
	position += velocity; 
	sprite.setPosition(position); 
}

void Player::draw(sf::RenderWindow& window) {
	window.draw(sprite);
}

void Player::handleCollision() {
}

void Player::update(sf::RenderWindow& window) {
	position += velocity;
	// Handle border collision
	if (position.x < 0) {
		position.x = 0;
	}
	else if (position.x > window.getSize().x - sprite.getGlobalBounds().width) {
		position.x = window.getSize().x - sprite.getGlobalBounds().width;
	}
		

	sprite.setPosition(position);
	velocity = sf::Vector2f(0, 0); 
}

sf::FloatRect Player::getBounds() const {
	return sprite.getGlobalBounds();
}

int Player::getHealth() {
	return health; 
}

void Player::setHealth(int Health) {
	this->health = Health; 
}

sf::Vector2f Player::getPosition() const {
	return position;
}

void Player::fire(std::vector<Bullet>& bullets, const sf::Texture& bulletTexture) {
	sf::Vector2f bulletVelocity(0.0f, -1.0f);
	bullets.emplace_back(getPosition(), bulletVelocity, bulletTexture);
}