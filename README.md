# DreamWallpaper

Приложения для установки обоев на рабочий стол Android приложений.

## Обзор приложения:

При запуске приложения пользователя встречает **Splash** экран. После Splash экрана загружается список из картинок.  
**Список прогружается только с использованием VPN.**
Приложение поддерживает дневную/ночную темы.
<p align="center">
  <img src="https://github.com/IBRUTALI/DreamWallpaper/assets/96013243/a155b789-d7b0-4a9b-bc01-7a688346c78f" width="250" height="525" />
  <h4 align="center">Splash экран и категории</h4>
</p>  

Для случаев, когда **отсутствует** подключение к **VPN/Интернету** или при ошибке сервера, реализован обработчик ошибок с помощью **Sealed** классов и **wrapper**'ов
<p align="center">
  <img src="https://github.com/IBRUTALI/DreamWallpaper/assets/96013243/697dc645-768d-4e19-90e0-23c9bf964dd8" width="250" height="525" />
  <h4 align="center">Ошибка подключения</h4>
</p>

Прогрузка изображения в списке и деталях изображения осуществляестя с помощью **Glide**.  
Есть возможность перемещаться между страницами списка. При клике на любой элемент списка - открывается экран с выбранным элементом в большем разрешении.
<p align="center">
  <img src="https://github.com/IBRUTALI/DreamWallpaper/assets/96013243/9606e072-7722-4f71-a7c1-be7c7ef912a2" width="250" height="525" />
  <h4 align="center">Список изображений</h4>
</p>

Изображение можно зумить. Реализован **DoubleTap** для зума, свайп вверх для **закрытие** экрана с изображением и плавные анимации для зума с помощью **ValueAnimator**.  
Функционал зума сделан с помощью **CustomView**, которая наследуется от ImageView.
<p align="center">
  <img src="https://github.com/IBRUTALI/DreamWallpaper/assets/96013243/1117066c-005a-444c-b5e4-dc4da47c82f2" width="250" height="525" />
  <h4 align="center">Зуминг изображения</h4>
</p>

Изображение можно **скачивать** на устройство или устанавливать сразу как **обои** на рабочий стол без скачивания. 
Скачавание реализовано с помощью **WorkManager**'a.
Для этих действий требуется разрешение от пользователя.
<p align="center">
  <img src="https://github.com/IBRUTALI/DreamWallpaper/assets/96013243/71b5a7ce-eb66-479e-bac3-5d1ab6267244" width="250" height="525" />
  <h4 align="center">Скачивание изображения</h4>
</p>

## Технологии:
- Для запроса изображения из сети - Retrofit2
- Для навигации Jetpack Navigation.
- DI - Dagger2.
- Архитектура - MVVM.
- Для загрузки изображений - Glide.
