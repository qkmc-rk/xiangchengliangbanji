@echo off
setlocal

set HOST=xiangcheng.ruankun.xyz
set USER=root
set PASSWORD=@Sicau211
set REMOTE_SCRIPT=/root/xiangchengfront/xiangchengliangbanji/xiangchengfront/start.sh
set COMMIT_MSG="script auto commit"
set REMOTE_NAME=gitee

echo Adding changes...
git add .

echo Committing changes with message %COMMIT_MSG%...
git commit -m %COMMIT_MSG%

echo Pushing changes to remote repository %REMOTE_NAME%...
git push %REMOTE_NAME%

echo Running script on %HOST%...

plink -pw %PASSWORD% %USER%@%HOST% "%REMOTE_SCRIPT%"

echo Script execution complete.

echo Pushing changes to remote repository github...

git push

echo push to github complete.