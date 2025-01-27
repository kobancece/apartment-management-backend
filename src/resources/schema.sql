CREATE TABLE IF NOT EXISTS roles (
  roleID INT PRIMARY KEY,
  roleName VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
  userID VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phoneNumber VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  roleType VARCHAR(255) NOT NULL
);
