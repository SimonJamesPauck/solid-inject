#!/bin/sh

set -e

BUMP_MAJOR=0
BUMP_MINOR=0
BUMP_PATCH=0

while [ "$1" != "" ]; do
    case $1 in
       --version )
          shift
          VERSION=$1
       ;;
       --major )
          BUMP_MAJOR=1
       ;;
       --minor )
          BUMP_MINOR=1
       ;;
       --patch )
          BUMP_PATCH=1
       ;;
    esac
    shift
done

if [ -z "$VERSION" ]
then
  echo "--version must be defined" >&2
  exit 1
fi

MAJOR=$(echo "$VERSION" | sed 's/^\([1-9][0-9]*\)\.[1-9][0-9]*\.[1-9][0-9]*$/\1/')
MINOR=$(echo "$VERSION" | sed 's/^[1-9][0-9]*\.\([1-9][0-9]*\)\.[1-9][0-9]*$/\1/')
PATCH=$(echo "$VERSION" | sed 's/^[1-9][0-9]*\.[1-9][0-9]*\.\([1-9][0-9]*\)$/\1/')

echo $MAJOR
echo $MINOR
echo $PATCH
