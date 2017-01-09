#!/bin/bash

# This script is used by travis-ci to publish javadoc and versions artifacts

REPO_SLUG="jchampemont/keywords-extractor"
MVN_REPO_SLUG="jchampemont/maven-repository"
JAVADOC_REPO_SLUG="jchampemont/javadoc"

if [ "$TRAVIS_REPO_SLUG" == "$REPO_SLUG" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"

  if [ -n "$TRAVIS_TAG" ]; then
    echo -e "Publishing maven artifacts...\n"
    ./gradlew -PghToken=$GH_TOKEN publish
    cd $HOME/.gitRepos/$MVN_REPO_SLUG
    git add .
    git commit -m "Publish artifacts of $1 $TRAVIS_TAG on sucessful travis build $TRAVIS_BUILD_NUMBER"
    git push
    cd -
  fi

  echo -e "Publishing javadoc...\n"

  cp -R build/docs/javadoc $HOME/$1-javadoc-latest

  cd $HOME
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/$JAVADOC_REPO_SLUG gh-pages > /dev/null

  mkdir -p gh-pages/$1
  cd gh-pages/$1
  cp -Rf $HOME/$1-javadoc-latest .
  if [ -z "$TRAVIS_TAG" ]; then
    git rm -rf $TRAVIS_BRANCH
    mv $1-javadoc-latest $TRAVIS_BRANCH
  else
    git rm -rf $TRAVIS_TAG
    mv $1-javadoc-latest $TRAVIS_TAG
  fi
  git add -f .
  git commit -m "Latest javadoc for $1 on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"

fi