CREATE TABLE `events` (
                          `event_id` int,
                          `event_name` varchar(255),
                          `start_date` datetime,
                          `end_date` datetime,
                          `status` enum('planned','ongoing','completed')
);

CREATE TABLE `coupons` (
                           `coupon_id` int,
                           `type_id` int,
                           `coupon_code` varchar(255),
                           `description` varchar(255),
                           `amount` decimal(10,2),
                           `valid_from` datetime,
                           `valid_to` datetime,
                           `total_quantity` int,
                           `remaining_quantity` int,
                           `status` enum('active','expired','depleted'),
                           FOREIGN KEY (`description`) REFERENCES `events`(`start_date`)
);

CREATE TABLE `users` (
                         `user_id` int,
                         `email` varchar(255),
                         `password_hash` varchar(255),
                         `username` varchar(255),
                         `status` enum('active','inactive'),
                         `registered_at` datetime
);

CREATE TABLE `coupon_issues` (
                                 `issue_id` int,
                                 `user_id` int,
                                 `coupon_id` int,
                                 `status` enum('issued','redeemed','expired'),
                                 `initial_amount` decimal(10,2),
                                 `remaining_amount` decimal(10,2),
                                 `issued_at` datetime,
                                 FOREIGN KEY (`coupon_id`) REFERENCES `users`(`password_hash`)
);

CREATE TABLE `coupon_types` (
                                `type_id` int,
                                `type_name` varchar(255),
                                `description` varchar(255)
);

