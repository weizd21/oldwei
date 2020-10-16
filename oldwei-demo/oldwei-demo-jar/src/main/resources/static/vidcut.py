import argparse
import cv2


def main(args):
#   print("--vidPath {0}".format(args.vidPath))  # args.address会报错，因为指定了dest的值
#   print("--cutPath {0}".format(args.cutPath))  # 如果命令行中该参数输入的值不在choices列表中，则报错
#    print("--vidLenth {0}".format(args.vidLenth))  # prot的类型为int类型，如果命令行中没有输入该选项则报错
    cap = cv2.VideoCapture(args.vidPath)
    cap.isOpened()
    width = cap.get(cv2.CAP_PROP_FRAME_WIDTH)
    height = cap.get(cv2.CAP_PROP_FRAME_HEIGHT)
 #   print(width, height)  #输出分辨率

    if cap.isOpened():
        rate = cap.get(cv2.CAP_PROP_FPS)
        FrameNumber = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
        tmfps = int(rate * int(args.vidLenth) * 60)
#        print(tmfps)     #切割后小视频帧数
#        print('################')
        i = 0
        while (True):
            success, frame = cap.read()
            if success:
                i += 1
                # print("当前帧数为：%", i)
                if (i % tmfps == 1):
                    videoWriter = cv2.VideoWriter(args.cutPath + str(i) + '.mp4',
                                                  cv2.VideoWriter_fourcc('D', 'I', 'V', 'X'), rate,
                                                  (int(width), int(height)))
#                    print("********************")
                    videoWriter.write(frame)
                else:
                    # print("######################")
                    videoWriter.write(frame)
                    # print("该段用时：%s", int(tm3 - tm2))
                    #pass
            else:
                print('end')    #视频切割完成
                break
#        print("视频总帧数：%", int(FrameNumber))
#        print("视频帧率：%", int(rate))
    else:
        print("video is not find")    #视频文件不存在


    cap.release()


if __name__ == '__main__':
    parser = argparse.ArgumentParser(usage="it's usage tip.", description="help info.")
    parser.add_argument("--vidPath", help="the port number.")
    parser.add_argument("--cutPath", help="the port number.")
    parser.add_argument("--vidLenth", help="the port number.")
    try:
        args = parser.parse_args()
        main(args)
    except Exception as e:
        print(e)     #输出捕获的异常
