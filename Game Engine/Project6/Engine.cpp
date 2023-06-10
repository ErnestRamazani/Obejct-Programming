#include "Engine.h"

Engine::Engine() : window(sf::VideoMode(1600, 900), "Game Engine"), score(0), level(1) {

    score = 100; 
    level = 1; 
    if (!font.loadFromFile("C:\\Windows\\Fonts\\arial.ttf")) {
        // handle error
    }

    scoreText.setFont(font);
    levelText.setFont(font);
    scoreText.setCharacterSize(24);
    levelText.setCharacterSize(24);
    scoreText.setPosition(10, 10);
    levelText.setPosition(10, 40);

    // Load the texture for the background
    sf::Texture* backgroundTexture = new sf::Texture();
    if (!backgroundTexture->loadFromFile("background.png")) {
        // Error...
    }
    // Scale the background to fit the window

    sf::Texture* obstacleTexture = new sf::Texture();
    if (!obstacleTexture->loadFromFile("target.png")) {
        // Error...
    }

    // Create obstacles
    for (int i = 0; i < 10; i++) { // creates 10 obstacles
        Obstacle* obstacle = new Obstacle(*obstacleTexture);
        obstacle->setPosition(rand() % window.getSize().x, rand() % window.getSize().y); // place the obstacle at a random position
        obstacles.push_back(obstacle);
    }



    sf::Vector2u windowSize = window.getSize();
    sf::Vector2u textureSize = backgroundTexture->getSize();
    float scaleX = static_cast<float>(windowSize.x) / textureSize.x;
    float scaleY = static_cast<float>(windowSize.y) / textureSize.y;
    background.setTexture(*backgroundTexture);
    background.scale(scaleX, scaleY);
    
    // Load the texture for the entity
    sf::Texture* entityTexture = new sf::Texture();
    if (!entityTexture->loadFromFile("sprite.png")) {
        // Error...
    }
    entity = new Entity(*entityTexture, sf::Vector2f(window.getSize().x, window.getSize().y));
    entity->setVelocity(sf::Vector2f(150, 150));
    acceleration = 1.0f;

    // Load the sound
    if (!buffer.loadFromFile("sound.wav")) {
        // Error...
    }
    sound.setBuffer(buffer);

    // Start the timer
    timer.restart();
}

Engine::~Engine() {
    delete entity;
    for (Obstacle* obstacle : obstacles) {
        delete obstacle;
    }
}

void Entity::bounce() {
    velocity = -velocity;  // Reverse the velocity
}

void Engine::run() {
    sf::Vector2f velocity;
    while (window.isOpen()) {
        sf::Event event;
        scoreText.setString("Score: " + std::to_string(score));
        levelText.setString("Level: " + std::to_string(level));
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed)
                window.close();
        }

        // Check for joystick connectivity and handle input
        if (sf::Joystick::isConnected(0)) {
            float x = sf::Joystick::getAxisPosition(0, sf::Joystick::X);
            float y = sf::Joystick::getAxisPosition(0, sf::Joystick::Y);

            // Normalize joystick input to range [-1, 1]
            x /= 100.0f;
            y /= 100.0f;

            // Apply joystick input to entity velocity
            velocity.x += x * acceleration;
            velocity.y += y * acceleration;

            entity->setVelocity(velocity);
        }
        // Keyboard input
        float speed = 250.0f; // You can adjust this value to make the entity move faster or slower
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Up)) {
            entity->setVelocity(sf::Vector2f(velocity.x, -speed));
        }
        else if (sf::Keyboard::isKeyPressed(sf::Keyboard::Down)) {
            entity->setVelocity(sf::Vector2f(velocity.x, speed));
        }
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left)) {
            entity->setVelocity(sf::Vector2f(-speed, velocity.y));
        }
        else if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right)) {
            entity->setVelocity(sf::Vector2f(speed, velocity.y));
        }


        sf::Time deltaTime = timer.restart();
        entity->update(deltaTime);

        window.clear();
        window.draw(background);
        window.draw(*entity);

        for (Obstacle* obstacle : obstacles) {
            window.draw(*obstacle);
        }

        // Handle collisions with obstacles
        for (Obstacle* obstacle : obstacles) {
            if (entity->getBound().intersects(obstacle->getBound())) {
                score--; // decrease the score
                entity->bounce(); // make the entity bounce
            }
        }


        scoreText.setString("Score: " + std::to_string(score));
        levelText.setString("Level: " + std::to_string(level));
        window.draw(scoreText);
        window.draw(levelText);

        window.display();

        // Play the sound every 10 seconds
        if (timer.getElapsedTime().asSeconds() >= 10.f) {
            sound.play();
            timer.restart();
            score++;
            if (score >= 10) {
                level++;
                score = 0;
            }
        }
    }
}
