const gulp = require('gulp');
const sass = require('gulp-sass')(require('sass'));
const cleanCSS = require('gulp-clean-css');
const minify = require('gulp-minify');
const concat = require('gulp-concat');

function compileSass(){
    return gulp.src('./src/main/resources/static/scss/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest('./src/main/resources/static/css'));
}

function minifyCss() {
    return gulp.src('./src/main/resources/static/css/**/*.css')
        .pipe(cleanCSS({compatibility: 'ie8'}))
        .pipe(gulp.dest('./src/main/resources/static/dist'));
}

function minifyJs() {
    return gulp.src('./src/main/resources/static/js/**/*.js')
        .pipe(minify())
        .pipe(gulp.dest('./src/main/resources/static/dist'));
}

function concatCss(){
    return gulp.src('./src/main/resources/static/css/**/*.css')
        .pipe(concat('styles.css'))
        .pipe(gulp.dest('./src/main/resources/static/dist'));
}

exports.default = gulp.parallel(gulp.series(compileSass,minifyCss));
exports.build = gulp.parallel(gulp.series(compileSass,minifyCss));
