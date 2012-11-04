<?php
/**
* @file index.php
* @brief entrance of etframe
* @author taoeaten@163.com CHN
* @version 0.1
* @date 2012-11-04
*/

//todo
/*
autoload php classes
requeset params split
hand out the real request to real module
do some log work
do some performace analysis
*/

echo "<p align=center >welcome to choose e.t frame!</p>";
echo "<p align=center >author:taoeaten</p>";
echo "<p align=center >_SERVER</p>";
var_dump($_SERVER);
echo "<p align=center >_REQUEST</p>";
var_dump($_REQUEST);
echo "<p align=center >_GET</p>";
var_dump($_GET);
echo "<p align=center >_POST</p>";
var_dump($_POST);
