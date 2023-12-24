# rf-boot

[re-frame](https://day8.github.io/re-frame/) project template 

Requirements:
- [deps-new](https://github.com/seancorfield/deps-new)
- Node.js for eg. via [nvm](https://github.com/nvm-sh/nvm)

## Usage
Replace `your/app-name` and run:
```sh
clj -Sdeps '{:deps {io.github.orchid-mantis/rf-boot {:git/sha "c2b5156b571d9b6d5a4573ed9d96093ca647a566"}}}' -Tnew create  :template orchid-mantis/rf-boot :name your/app-name
```

## Install
```sh
cd app-name
nvm use
corepack enable
yarn install
```
