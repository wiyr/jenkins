#!/bin/bash
set -o errexit
set -o nounset
set -o pipefail

DINGDING_WEBHOOK="https://oapi.dingtalk.com/robot/send?access_token=4988bf6375045cee3de6b184b66b3ac1d3eb267c0b75ed2f3f41d2e1baf503b7"
START_BUILD_PIC="http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/sign-sync-icon.png"
SUCCESS_BUILD_PIC="http://icons.iconarchive.com/icons/cheezen/web-2/128/check-icon.png"
FAILED_BUILD_PIC="http://icons.iconarchive.com/icons/awicons/vista-artistic/128/delete-icon.png"

picUrl=$START_BUILD_PIC
if [ $3 -eq 1 ]
then
    picUrl=$SUCCESS_BUILD_PIC
fi
if [ $3 -eq 2 ]
then
    picUrl=$FAILED_BUILD_PIC
fi
echo $picUrl

curl -H "Content-Type: application/json" -d "{\"msgtype\": \"link\",
\"link\": {\"title\": \"$1\",
\"text\": \"$1\",
\"picUrl\": \"$picUrl\",
\"messageUrl\": \"$2\"}}" $DINGDING_WEBHOOK
