#include <sstream>  
#include<cstdlib>
#include <iostream>
#include<SFML/Audio.hpp>

using std::stringstream;

#include "Player.h"
#include "Enemy.h"
#include "Bullet.h"
#include "PowerUp.h"

int main() {
    sf::SoundBuffer bulletSoundBuffer;
    sf::SoundBuffer powerupSoundBuffer;
    sf::Music backgroundMusic;
    sf::Clock bulletClock;
    sf::Clock gameClock;
    sf::Clock powerupClock;
    sf::Clock enemySpawnClock;

    bool restart = false;
    bool bombCollected = false;

    int score = 0;

    float currentEnemySpeedFactor = 1.0f;
    int enemyCountFactor = 1;
    const float TIME_TO_INCREASE_SPEED = 10.f;
    const float SPEED_INCREMENT = 1.5f;

    sf::RenderWindow window(sf::VideoMode(800, 600), "Final Project");

    sf::Sprite backgroundSprite;

    // background.setFillColor(sf::Color::Black);


    sf::Texture playerTexture, bulletTexture, enemyTexture,
        bombTexture, doubleScoreTexture, extraLifeTexture, backgroundTexture;

    if (!playerTexture.loadFromFile("playerTexture.png") ||
        !bulletTexture.loadFromFile("bulletTexture.png") ||
        !enemyTexture.loadFromFile("enemyTexture.png") ||
        !bombTexture.loadFromFile("bombTexture.png") ||
        !doubleScoreTexture.loadFromFile("doubleScoreTexture.png") ||
        !extraLifeTexture.loadFromFile("extraLifeTexture.png") ||
        !backgroundTexture.loadFromFile("backgroundTexture.png")) {
        // handle error
    }
    float scaleX = static_cast<float>(window.getSize().x) / backgroundTexture.getSize().x;
    float scaleY = static_cast<float>(window.getSize().y) / backgroundTexture.getSize().y;
    backgroundSprite.setScale(scaleX, scaleY);

    backgroundSprite.setTexture(backgroundTexture);


    Player player(sf::Vector2f(400, 450), playerTexture);

    if (!backgroundMusic.openFromFile("backgroundMusic.ogg") ||
        !bulletSoundBuffer.loadFromFile("bulletSound.ogg") ||
        !powerupSoundBuffer.loadFromFile("powerupSound.ogg")) {
        //error
    }
    backgroundMusic.setLoop(true);
    backgroundMusic.play();

    sf::Sound bulletSound;
    bulletSound.setBuffer(bulletSoundBuffer);
    bulletSound.setVolume(100);
    sf::Sound powerupSound;
    powerupSound.setBuffer(powerupSoundBuffer);

    // Suppose we have a container of bullets and enemies
    std::vector<Bullet> bullets;
    std::vector<Enemy> enemies;
    std::vector<PowerUp*> powerups;



    for (int i = 0; i < 10; ++i) {
        bullets.emplace_back(sf::Vector2f(400, 200), sf::Vector2f(0, -1), bulletTexture);
    }
    for (int i = 0; i < 5; ++i) {
        enemies.emplace_back(sf::Vector2f(i * 150, 50), enemyTexture);
    }


    sf::Font font;
    if (!font.loadFromFile("C:\\Windows\\Fonts\\arial.ttf")) {
        // error handling
    }

    sf::Text scoreText;
    sf::Text gameOverText;

    scoreText.setFont(font);
    scoreText.setCharacterSize(24); // in pixels
    scoreText.setFillColor(sf::Color::White);
    scoreText.setPosition(400, 0);

    gameOverText.setFont(font);
    gameOverText.setCharacterSize(32); // in pixels
    gameOverText.setFillColor(sf::Color::Red);
    gameOverText.setString("Game Over \n Press R to Restart the Game");
    gameOverText.setPosition(window.getSize().x / 2 - gameOverText.getLocalBounds().width / 2,
        window.getSize().y / 2 - gameOverText.getLocalBounds().height / 2);


    sf::Text healthText;
    healthText.setFont(font);
    healthText.setCharacterSize(24); // in pixels
    healthText.setFillColor(sf::Color::White);

    while (window.isOpen() && !restart) {
        sf::Event event;

        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed) {
                window.close();
            }
        }

        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Space)) {
            if (bulletClock.getElapsedTime().asSeconds() > 0.2f) { // Only allow a bullet to be fired every 0.2 seconds
                bullets.emplace_back(player.getPosition(), sf::Vector2f(0, -1), bulletTexture); // Use emplace_back instead of push_back
                bulletClock.restart(); // Reset the clock when a bullet is fired
                bulletSound.play();
            }
        }


        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left)) {
            // Move player left
            player.move(sf::Vector2f(-0.1, 0));
        }

        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right)) {
            // Move player right
            player.move(sf::Vector2f(0.1, 0));
        }

        if (gameClock.getElapsedTime().asSeconds() > TIME_TO_INCREASE_SPEED) {
            currentEnemySpeedFactor *= SPEED_INCREMENT; // Double the speed factor
            for (Enemy& enemy : enemies) {
                enemy.increaseSpeed(currentEnemySpeedFactor); // Pass the speed factor to enemies
            }
            gameClock.restart(); // Reset the clock
        }


        //Update game state
        player.update(window);


        for (Bullet& bullet : bullets) {
            bullet.update();
        }

        for (Enemy& enemy : enemies) {
            enemy.update(window);
        }


        // Collision checks
          // Collision checks
        for (auto bullet_it = bullets.begin(); bullet_it != bullets.end(); ) {
            Bullet& bullet = *bullet_it;
            bullet.update();

            bool bullet_collided_with_enemy = false;
            for (auto enemy_it = enemies.begin(); enemy_it != enemies.end(); ) {
                Enemy& enemy = *enemy_it;

                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    bullet_collided_with_enemy = true;
                    enemy_it = enemies.erase(enemy_it);
                    enemies.emplace_back(sf::Vector2f(rand() % 800, 0), enemyTexture, currentEnemySpeedFactor);
                    score += 1;
                }

                else {
                    ++enemy_it;
                }
            }

            // If bullet has gone off the screen, or it hit an enemy, remove it
            if (bullet.getPosition().y < 0 || bullet_collided_with_enemy) {
                bullet_it = bullets.erase(bullet_it);
            }
            else {
                ++bullet_it;
            }
        }


        // Similarly, update enemy loop
        for (auto it = enemies.begin(); it != enemies.end(); ) {
            Enemy& enemy = *it;

            // Update enemy and handle collision with player
            enemy.update(window);
            if (enemy.getBounds().intersects(player.getBounds())) {
                player.setHealth(player.getHealth() - 20);
                it = enemies.erase(it);
                for (int i = 0; i < enemyCountFactor; i++) {
                    enemies.emplace_back(sf::Vector2f(rand() % 800, 0), enemyTexture, currentEnemySpeedFactor);
                }
            }

            else if (enemy.getPosition().y >= window.getSize().y) {
                if (!enemy.getHasImpacted()) {
                    player.setHealth(player.getHealth() - 10);
                    enemy.setHasImpacted(true);
                    std::cout << "Enemy reached bottom, player health: " << player.getHealth() << std::endl;
                }
                enemy.setPosition(sf::Vector2f(rand() % 800, 0));  // Reset enemy position
                it = enemies.erase(it);
                enemies.emplace_back(sf::Vector2f(rand() % 800, 0), enemyTexture);
            }


            else {
                ++it;
            }
        }

        // Inside your game loop

// Update powerups and generate new ones every 5 seconds
        if (powerupClock.getElapsedTime().asSeconds() >= 5.f) { // Check if 5 seconds have passed
            int powerupType = rand() % 3;
            sf::Vector2f position(rand() % 800, 0); // Random x position at the top of the screen
            if (powerupType == 0) {
                PowerUp* bomb = new Bomb(position, bombTexture);
                bomb->setDirection(sf::Vector2f(0, 1));
                bomb->setSpeed(0.05f);
                powerups.push_back(bomb);
            }
            else if (powerupType == 1) {
                PowerUp* doubleScore = new DoubleScore(position, doubleScoreTexture);
                doubleScore->setDirection(sf::Vector2f(0, 1));
                doubleScore->setSpeed(0.05f);
                powerups.push_back(doubleScore);
            }
            else {
                PowerUp* extraLife = new ExtraLife(position, extraLifeTexture);
                extraLife->setDirection(sf::Vector2f(0, 1));
                extraLife->setSpeed(0.05f);
                powerups.push_back(extraLife);
            }
            powerupClock.restart(); // Reset the clock
        }

        // Updating and drawing power-ups
           // Updating and drawing power-ups
      // Updating and drawing power-ups
        for (auto powerup_it = powerups.begin(); powerup_it != powerups.end(); ) {
            PowerUp* powerup = *powerup_it;
            powerup->update(); // Update power-up position



            if (powerup->getBounds().intersects(player.getBounds())) {
                powerup->activate(enemies, player, score);
                powerupSound.play(); // Play the powerup sound
                delete powerup;
                powerup_it = powerups.erase(powerup_it);
            }
            else if (powerup->getPosition().y > window.getSize().y) { // If powerup goes beyond screen, remove it
                delete powerup;
                powerup_it = powerups.erase(powerup_it);
            }
            else {
                ++powerup_it;
            }
        }

        // After Bomb Collection
        for (auto powerup_it = powerups.begin(); powerup_it != powerups.end(); ) {
            PowerUp* powerup = *powerup_it;
            powerup->update(); // If you want power-ups to move or animate

            if (powerup->getBounds().intersects(player.getBounds())) {
                powerup->activate(enemies, player, score);

                // Check if the powerup was a Bomb
                Bomb* bomb = dynamic_cast<Bomb*>(powerup);
                if (bomb != nullptr) {
                    bombCollected = true;
                    enemySpawnClock.restart();
                }

                delete powerup;
                powerup_it = powerups.erase(powerup_it);
            }
            else {
                ++powerup_it;
            }
        }

        // Before spawning new enemies, check if a bomb has been collected recently
        if (bombCollected && enemySpawnClock.getElapsedTime().asSeconds() < 2.f) {
            continue;
        }
        // Draw everything


        window.clear();
        window.draw(backgroundSprite);
        std::stringstream scoreSS;
        scoreSS << "Score: " << score;
        scoreText.setString(scoreSS.str());


        std::stringstream healthSS;
        healthSS << "Health: " << player.getHealth();
        healthText.setString(healthSS.str());

        // Draw score text
        window.draw(scoreText);

        // Draw health text
        window.draw(healthText);


        player.draw(window);

        for (Bullet& bullet : bullets) {
            bullet.draw(window);
        }

        for (Enemy& enemy : enemies) {
            enemy.draw(window);
        }


        for (PowerUp* powerup : powerups) {
            powerup->draw(window);
        }

        window.display();
        // Game Over Condition
        if (player.getHealth() <= 0) {
            break; // Game Over, exit the game loop
        }
    }
    // New game over loop :
    while (window.isOpen()) {
        sf::Event event;
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed) {
                window.close();
            }

            // Restart condition (e.g., when pressing the "R" key)
            if (event.type == sf::Event::KeyPressed && event.key.code == sf::Keyboard::R) {
                restart = true;
                break;
            }
        }

        if (restart || !window.isOpen()) {
            break;
        }

        window.clear();
        window.draw(backgroundSprite);
        window.draw(gameOverText);
        window.display();
    }

    if (restart) {
        main(); // Recursive call to main function, starting the game again
    }

    return 0;
}